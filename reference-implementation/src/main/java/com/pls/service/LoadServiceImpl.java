package com.pls.service;

import java.math.BigDecimal;
import java.util.Calendar;
import java.util.List;
import java.util.Vector;

import javax.ejb.Stateless;

import com.pls.domain.Carrier;
import com.pls.domain.CarrierStatus;
import com.pls.domain.Customer;
import com.pls.domain.CustomerStatus;
import com.pls.domain.Load;

/**
 * Load service impl.
 * 
 * @author User
 *
 */
@Stateless
public class LoadServiceImpl implements LoadService {
	private static final int MAGIC_NUMBER = 50;
	
	private final List<Load> loads;

	/**
	 * Default constructor.
	 */
	public LoadServiceImpl() {
		loads = new Vector<Load>(MAGIC_NUMBER);
		for (long i = 0; i < MAGIC_NUMBER; i++) {
			loads.add(createLoad(i));
		}
	}

	@Override
	public Load getById(Long id) {
		for (Load load : loads) {
			if (load.getId().equals(id)) {
				return load;
			}
		}

		return null;
	}

	@Override
	public List<Load> getAllLoads() {
		return loads;
	}

	@Override
	public void addLoad(Load load) {
		loads.add(load);
	}

	/**
	 * Create new instance.
	 * @param id 
	 * @return new instance.
	 */
	private static Load createLoad(Long id) {
		Carrier carrier = new Carrier();
		carrier.setId(id);
		carrier.setContactName("Kolya Ananymous");
		carrier.setName("Roga i Kopita");
		carrier.setStatus(CarrierStatus.ACTIVE);
		carrier.setTaxId("0000-1111-2222");
		carrier.setValidUntil(Calendar.getInstance().getTime());
		carrier.setMcNumber(Long.valueOf(MAGIC_NUMBER));
		carrier.setScac("KANN");

		Customer customer = new Customer();
		customer.setId(id);
		customer.setContactName("Vasya Pupkin");
		customer.setName("Pupkin & Sons");
		customer.setStatus(CustomerStatus.ACTIVE);
		customer.setTaxId("0000-1111-2222");
		customer.setValidUntil(Calendar.getInstance().getTime());

		Load load = new Load();
		load.setId(id);
		load.setCarrier(carrier);
		load.setCustomer(customer);
		load.setCarrierRate(new BigDecimal(MAGIC_NUMBER));
		load.setCustomerRate(new BigDecimal(MAGIC_NUMBER));
		load.setPiecies(MAGIC_NUMBER);
		load.setShipmentNumber("123654789554");
		load.setWeight(MAGIC_NUMBER);

		return load;
	}
}
