package com.serviside.CashDispenseService;

import com.serverside.CashDispenseDao.CashDepenserDao;
import com.serverside.Exceptions.IncorrectRandValeException;
import com.serverside.Exceptions.InvalidLoginException;
import com.serverside.domain.Login;
import com.serviside.ICashDispenseService.ICashDenpenserService;

public class CashDispenseService implements ICashDenpenserService {

	private CashDepenserDao cashDepenserDao = new CashDepenserDao();

	@Override
	public void loginUser(Login login) throws InvalidLoginException {
		System.out.println("loginUser method called in service");
		cashDepenserDao.loginUser(login);
	}

	@Override
	public StringBuilder calculateDispanseAmount(Float buyingAmount, Float amountDue) throws IncorrectRandValeException {
		System.out.println("calculateDispanseAmount method called in service");
		return cashDepenserDao.calculateDispanseAmount(buyingAmount, amountDue);
	}

}
