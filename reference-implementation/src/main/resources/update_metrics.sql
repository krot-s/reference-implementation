insert into freight_solns_metrics(METRICS_ID, PERSON_ID, TEAM_ID, BRANCH_ID, LOADS, TOTAL_REVENUE, ACTUAL_REVENUE, MARGIN, COST, METRICS_TYPE)
	select freight_solns_metrics_seq.NEXTVAL, tac, team_id, branch_id, loads, total_revenue, revenue, margin, costs, stat_type
 	from (
 	select 
      tac, 
      case when (select tu.team_id from team_user tu where tu.status = 'A' and sysdate >= tu.eff_date and sysdate < tu.exp_date and person_id = tac) is not null then
      	 (select tu.team_id from team_user tu where tu.status = 'A' and sysdate >= tu.eff_date and sysdate < tu.exp_date and person_id = tac) 
      	 else (select t.team_id from team t where t.name = 'MISSING TEAM') end as team_id,
      case when (select tu.team_id from team_user tu where tu.status = 'A' and sysdate >= tu.eff_date and sysdate < tu.exp_date and person_id = tac) is not null then
      	(select b.branch_id from branch b inner join team t on b.branch_id = t.branch_id inner join team_user tu on t.team_id = tu.team_id where tu.status = 'A' and sysdate >= tu.eff_date and sysdate < tu.exp_date and person_id = tac) 
      	else (select t.branch_id from team t where t.name = 'MISSING TEAM') end as branch_id,
      sum(loadz) loads, sum(total_rev) total_revenue, sum(rev) revenue, sum(marg) margin, sum(costz) costs, 'WEEKLY' as stat_type
    from (

      /* this is for the customer owner */
      select
        count(lsd.load_id) loadz,
        sum(lcd.total_revenue) total_rev,
        sum(lcd.total_revenue * (lrs.lead_pct/100.0)) rev,
        sum((lcd.total_revenue * (lrs.lead_pct/100.0)) - (lcd.total_costs * (lrs.lead_pct/100.0))) marg,
        sum(lcd.total_costs) costz,
        uc.person_id tac
      from  load_search_data lsd
      join  loads on lsd.load_id = loads.load_id
      join  load_cost_details lcd on lsd.load_id = lcd.load_id
      join  load_revenue_sharing lrs on lsd.load_id = lrs.load_id
      join  user_customer uc on uc.org_id = lsd.org_id
      where lsd.load_status in ( 'PP','GA','AD','LD','A','CD' )
      and   lcd.status = 'A'
      and   uc.status = 'A'
      and   loads.award_date >= uc.eff_date
      and   loads.award_date < uc.exp_date
      and   nvl(lsd.orig_departure,lsd.orig_scheduled_arrival) + (NVL(lsd.orig_scheduled_arrival_tz,0)/24) >= next_day(trunc(sysdate),'SUNDAY') - 7 /* use trunc on sysdate so can still use index */
      and   nvl(lsd.orig_departure,lsd.orig_scheduled_arrival) + (NVL(lsd.orig_scheduled_arrival_tz,0)/24) < next_day(trunc(sysdate),'SUNDAY')
      and ( 
        select case when exists ( /* if there is a location specific owner*/
          select 'X'
          from   flatbed.user_customer uc2 
          where  uc2.location_id = lsd.location_id
          and    uc2.org_id = lsd.org_id
          and    uc2.status = 'A'
          and    loads.award_date >= uc2.eff_date
          and    loads.award_date < uc2.exp_date
        ) then ( /* use the location specific owner */
          select user_customer_id
          from   flatbed.user_customer uc3
          where  lsd.org_id = uc3.org_id
          and    lsd.location_id = uc3.location_id
          and    uc3.status = 'A'
          and    loads.award_date >= uc3.eff_date
          and    loads.award_date < uc3.exp_date
        ) else ( /* use the org owner */
          select user_customer_id
          from   flatbed.user_customer uc3
          where  lsd.org_id = uc3.org_id
          and    uc3.location_id is null
          and    uc3.status = 'A'
          and    loads.award_date >= uc3.eff_date
          and    loads.award_date < uc3.exp_date
	    ) end
        from dual
      ) = uc.user_customer_id
      group by uc.person_id
    )
    group by tac
    
    union all

    select
      tac,       
      (select tu.team_id from team_user tu where tu.status = 'A' and sysdate >= tu.eff_date and sysdate < tu.exp_date and person_id = tac) team_id,
      (select b.branch_id from branch b inner join team t on b.branch_id = t.branch_id inner join team_user tu on t.team_id = tu.team_id where tu.status = 'A' and sysdate >= tu.eff_date and sysdate < tu.exp_date and person_id = tac) branch_id,
      sum(loadz) loads, sum(total_rev) total_revenue, sum(rev) revenue, sum(marg) margin, sum(costz) costs, 'MONTHLY' as stat_type
    from (

      /* this is for the customer owner */
      select
        count(lsd.load_id) loadz,
        sum(lcd.total_revenue) total_rev,
        sum(lcd.total_revenue * (lrs.lead_pct/100.0)) rev,
        sum((lcd.total_revenue * (lrs.lead_pct/100.0)) - (lcd.total_costs * (lrs.lead_pct/100.0))) marg,
        sum(lcd.total_costs) costz,
        uc.person_id tac
      from  load_search_data lsd
      join  loads on lsd.load_id = loads.load_id
      join  load_cost_details lcd on lsd.load_id = lcd.load_id
      join  load_revenue_sharing lrs on lsd.load_id = lrs.load_id
      join  user_customer uc on uc.org_id = lsd.org_id
      where lsd.load_status in ( 'PP','GA','AD','LD','A','CD' )
      and   lcd.status = 'A'
     and   uc.status = 'A'
      and   loads.award_date >= uc.eff_date
      and   loads.award_date < uc.exp_date
      and nvl(lsd.orig_departure,lsd.orig_scheduled_arrival) + (NVL(lsd.orig_scheduled_arrival_tz,0)/24) >= to_date( to_char( sysdate, 'mm' ) || '/01/' || to_char( sysdate, 'yyyy' ), 'mm/dd/yyyy' )
      and nvl(lsd.orig_departure,lsd.orig_scheduled_arrival) + (NVL(lsd.orig_scheduled_arrival_tz,0)/24) < add_months( to_date( to_char( sysdate, 'mm' ) || '/01/' || to_char( sysdate, 'yyyy' ) , 'mm/dd/yyyy' ), 1 )  
      and ( 
        select case when exists ( /* if there is a location specific owner */
          select 'X'
          from   flatbed.user_customer uc2 
          where  uc2.location_id = lsd.location_id
          and    uc2.org_id = lsd.org_id
          and    uc2.status = 'A'
          and    loads.award_date >= uc2.eff_date
          and    loads.award_date < uc2.exp_date
        ) then ( /* use the location specific owner */
          select user_customer_id
          from   flatbed.user_customer uc3
          where  lsd.org_id = uc3.org_id
          and    lsd.location_id = uc3.location_id
          and    uc3.status = 'A'
          and    loads.award_date >= uc3.eff_date
          and    loads.award_date < uc3.exp_date
        ) else ( /* use the org owner */
          select user_customer_id
          from   flatbed.user_customer uc3
          where  lsd.org_id = uc3.org_id
          and    uc3.location_id is null
          and    uc3.status = 'A'
          and    loads.award_date >= uc3.eff_date
          and    loads.award_date < uc3.exp_date
	    ) end
       from dual
      ) = uc.user_customer_id 
      group by uc.person_id
    )
    group by tac
    )
