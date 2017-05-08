package com.serverside.CashDispenseDao;

import com.serverside.Exceptions.IncorrectRandValeException;
import com.serverside.Exceptions.InvalidLoginException;
import com.serverside.ICashDispenserDao.ICashDespenser;
import com.serverside.domain.Login;
import java.util.HashMap;
import java.util.Map;

public class CashDepenserDao implements ICashDespenser {

	private Map<String, String> map = new HashMap<String, String>() {
		{
			put("ogazana", "1234");
			put("nmbete", "4321");
		}
	};
	private StringBuilder sb = new StringBuilder();

	/**
	 * check if user is a registered user
	 *
	 * @param login
	 * @throws InvalidLoginException
	 */
	@Override
	public void loginUser(Login login) throws InvalidLoginException {

		boolean registeredUser = false;
		for (Map.Entry<String, String> entry : map.entrySet()) {
			if (login.getUsername().equals(entry.getKey()) && login.getPassword().equals(entry.getValue())) {
				registeredUser = true;
				System.out.println(login.getUsername() + " has successfully loggedin");
			}
		}
		if (!registeredUser) {

			throw new InvalidLoginException("The username or password is incorrect.");
		}
	}

	/**
	 * calculate and dispense amount
	 *
	 * @param buyingAmount
	 * @param amountDue
	 * @return
	 * @throws IncorrectRandValeException
	 */
	@Override
	public StringBuilder calculateDispanseAmount(Float buyingAmount, Float amountDue) throws IncorrectRandValeException {
		int[] rands = {100, 50, 20, 10};
		int[] coins = {5, 2, 1, 50, 20, 10, 5};
		int[] randCount = {0, 0, 0, 0};
		int[] countCoin = {0, 0, 0, 0, 0, 0, 0};

		if (buyingAmount.isNaN() || buyingAmount < 0) {
			throw new IncorrectRandValeException("The rand note value is incorrect");
		}
		else {

			float amount = buyingAmount - amountDue;
			float change = amount;
			for (int i = 0; i < rands.length; i++) {
				if (rands[i] <= amount) {
					randCount[i] = (int) (amount / rands[i]);
					amount = amount % rands[i];
				}
			}
			for (int i = 0; i < randCount.length; i++) {
				if (randCount[i] != 0) {
					String s = ("R" + rands[i] + " * " + randCount[i] + " = "
							+ (rands[i] * randCount[i]) + ",");
					sb.append(s);
				}
			}

			for (int i = 0; i < coins.length; i++) {
				if (coins[i] <= amount) {
					countCoin[i] = (int) (amount / coins[i]);
					amount = amount % coins[i];
				}
			}
			for (int i = 0; i < countCoin.length; i++) {
				if (countCoin[i] != 0 && countCoin[i] == 5 || countCoin[i] == 2 || countCoin[i] == 1) {
					String s = ("R" + coins[i] + " * " + countCoin[i] + " = "
							+ (coins[i] * countCoin[i]) + ",");
					sb.append(s);
				}
				else if (countCoin[i] != 0 && countCoin[i] == 50 || countCoin[i] == 20 || countCoin[i] == 10) {
					String s = (coins[i] + " * " + countCoin[i] + " = "
							+ (coins[i] * countCoin[i] + " Cents") + ",");
					sb.append(s);
				}
			}
			String s = ("Total R" + change);
			sb.append(s);
			System.out.println(sb.toString());
		}

		return sb;
	}

	public Map<String, String> getMap() {
		return map;
	}

	public void setMap(Map<String, String> map) {
		this.map = map;
	}

	public StringBuilder getSb() {
		return sb;
	}

	public void setSb(StringBuilder sb) {
		this.sb = sb;
	}

}
