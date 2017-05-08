package com.serverside.ICashDispenserDao;

import com.serverside.Exceptions.IncorrectRandValeException;
import com.serverside.Exceptions.InvalidLoginException;
import com.serverside.domain.Login;

public interface ICashDespenser {

	public void loginUser(Login login) throws InvalidLoginException;

	public StringBuilder calculateDispanseAmount(Float buyingAmount, Float amountDue) throws IncorrectRandValeException;

}
