package com.pls.service;

import java.util.Calendar;
import java.util.List;
import java.util.Vector;

import javax.ejb.Stateless;

import com.pls.domain.Carrier;
import com.pls.domain.CarrierStatus;

/**
 * Default impl.
 * 
 * @author User
 * 
 */
@Stateless
public class CarrierServiceImpl implements CarrierService {
	private static final int MAGIC_NUMBER = 50;

	private final List<Carrier> carriers;

	/**
	 * Default constructor.
	 */
	public CarrierServiceImpl() {
		carriers = new Vector<Carrier>(MAGIC_NUMBER);
		for (long i = 0; i < MAGIC_NUMBER; i++) {
			carriers.add(createCarrier(i));
		}
	}

	@Override
	public Carrier getById(Long id) {
		for (Carrier carrier : carriers) {
			if (carrier.getId().equals(id)) {
				return carrier;
			}
		}

		return null;
	}

	@Override
	public List<Carrier> getAllCarriers() {
		return carriers;
	}

	/**
	 * Create new instance.
	 * @param id 
	 * @return new instance.
	 */
	private static Carrier createCarrier(Long id) {
		Carrier carrier = new Carrier();
		carrier.setId(id);
		carrier.setContactName("Kolya Ananymous");
		carrier.setName("Roga i Kopita");
		carrier.setStatus(CarrierStatus.ACTIVE);
		carrier.setTaxId("0000-1111-2222");
		carrier.setValidUntil(Calendar.getInstance().getTime());
		carrier.setMcNumber(Long.valueOf(MAGIC_NUMBER));
		carrier.setScac("KANN");
		return carrier;
	}

	@Override
	public void addCarrier(Carrier carrier) {
		carriers.add(carrier);
	}
}
