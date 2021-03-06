package br.com.yapay.gateway.builder;

import java.util.ArrayList;
import java.util.List;

import br.com.yapay.gateway.model.AddressData;
import br.com.yapay.gateway.model.ChargingData;
import br.com.yapay.gateway.model.CreditCardData;
import br.com.yapay.gateway.model.DebitCardData;
import br.com.yapay.gateway.model.DeliveryData;
import br.com.yapay.gateway.model.ExtraField;
import br.com.yapay.gateway.model.ItemData;
import br.com.yapay.gateway.model.PhoneData;
import br.com.yapay.gateway.model.Transaction;
import br.com.yapay.gateway.model.TransactionData;

public class TransactionBuilder {

	private static Transaction transaction;

	public static Transaction build() {

		Transaction newTransaction = transaction;
		transaction = null;

		return newTransaction;
	}

	public static void newTransaction(String storeCode, Integer paymentCode, Long transactionNumber, Long value) {
		transaction = new Transaction();
		transaction.setTransactionData(new TransactionData());
		transaction.setStoreCode(storeCode);
		transaction.setPaymentCode(paymentCode);
		transaction.getTransactionData().setTransactionNumber(transactionNumber);
		transaction.getTransactionData().setValue(value);
	}

	public static void withInstallments(Integer installments) {
		transaction.getTransactionData().setInstallments(installments);
	}

	public static void withSingleCreditCard(String cardHolderName, String cardNumber, String cvv,
			String expirationDate) {
		transaction.setCreditCard(new CreditCardData());
		transaction.getCreditCard().setCardHolderName(cardHolderName);
		transaction.getCreditCard().setCardNumber(cardNumber);
		transaction.getCreditCard().setCvv(cvv);
		transaction.getCreditCard().setExpirationDate(expirationDate);
	}

	public static void withSingleDebitCard(String agency, String agencyDigit, String accountNumber,
			String accountNumberDigit, String accountType) {
		transaction.setDebitCard(new DebitCardData());
		transaction.getDebitCard().setAgency(agency);
		transaction.getDebitCard().setAgencyDigit(agencyDigit);
		transaction.getDebitCard().setAccountNumber(accountNumber);
		transaction.getDebitCard().setAccountNumberDigit(accountNumberDigit);
		transaction.getDebitCard().setAccountType(accountType);
	}

	public static void withItems(List<ItemData> listOfItems) {
		List<ItemData> transactionItemList = new ArrayList<>();

		if (listOfItems != null) {
			for (ItemData item : listOfItems) {
				ItemData transactionItem = new ItemData();
				transactionItem.setCategoryName(item.getCategoryName());
				transactionItem.setProductAmount(item.getProductAmount());
				transactionItem.setProductCategory(item.getProductCategory());
				transactionItem.setProductCode(item.getProductCode());
				transactionItem.setProductName(item.getProductName());
				transactionItem.setProductUnitaryValue(item.getProductUnitaryValue());
				transactionItemList.add(transactionItem);
			}
		}

		transaction.setItems(transactionItemList);
	}

	public static void withExtraFields(List<ExtraField> listOfExtraFields) {
		List<ExtraField> transactionExtraFieldsList = new ArrayList<>();

		if (listOfExtraFields != null) {
			for (ExtraField extraField : listOfExtraFields) {
				ExtraField transactionExtraField = new ExtraField();
				transactionExtraField.setKey(extraField.getKey());
				transactionExtraField.setValue(transactionExtraField.getValue());
				transactionExtraFieldsList.add(transactionExtraField);
			}
		}

		transaction.setExtraFields(transactionExtraFieldsList);
	}

	public static void withDelivery(DeliveryData deliveryData) {
		transaction.setDelivery(new DeliveryData());
		transaction.getDelivery().setBirthday(deliveryData.getBirthday());
		transaction.getDelivery().setDocument(deliveryData.getDocument());
		transaction.getDelivery().setDocumentTwo(deliveryData.getDocumentTwo());
		transaction.getDelivery().setMail(deliveryData.getMail());
		transaction.getDelivery().setName(deliveryData.getName());
		transaction.getDelivery().setSex(deliveryData.getSex());

		if (deliveryData.getDeliveryAddress() != null) {
			transaction.getDelivery().setDeliveryAddress(new AddressData());
			transaction.getDelivery().getDeliveryAddress().setCity(deliveryData.getDeliveryAddress().getCity());
			transaction.getDelivery().getDeliveryAddress()
					.setComplement(deliveryData.getDeliveryAddress().getComplement());
			transaction.getDelivery().getDeliveryAddress().setCountry(deliveryData.getDeliveryAddress().getCountry());
			transaction.getDelivery().getDeliveryAddress().setDistrict(deliveryData.getDeliveryAddress().getDistrict());
			transaction.getDelivery().getDeliveryAddress().setNumber(deliveryData.getDeliveryAddress().getNumber());
			transaction.getDelivery().getDeliveryAddress().setState(deliveryData.getDeliveryAddress().getState());
			transaction.getDelivery().getDeliveryAddress().setStreet(deliveryData.getDeliveryAddress().getStreet());
		}

		if (deliveryData.getDeliveryPhone() != null && !deliveryData.getDeliveryPhone().isEmpty()) {
			List<PhoneData> myPhoneList = new ArrayList<>();

			for (PhoneData phone : deliveryData.getDeliveryPhone()) {
				PhoneData myPhone = new PhoneData();
				myPhone.setDdd(phone.getDdd());
				myPhone.setDdi(phone.getDdi());
				myPhone.setPhone(phone.getPhone());
				myPhone.setPhoneType(phone.getPhoneType());
				myPhoneList.add(myPhone);
			}

			transaction.getDelivery().setDeliveryPhone(myPhoneList);
		}
	}

	public static void withCharging(ChargingData chargingData) {
		transaction.setCharging(new ChargingData());
		transaction.getCharging().setClientBirthday(chargingData.getClientBirthday());
		transaction.getCharging().setClientCode(chargingData.getClientCode());
		transaction.getCharging().setClientDocument(chargingData.getClientDocument());
		transaction.getCharging().setClientDocumentTwo(chargingData.getClientDocumentTwo());
		transaction.getCharging().setClientEmail(chargingData.getClientEmail());
		transaction.getCharging().setClientName(chargingData.getClientName());
		transaction.getCharging().setClientSex(chargingData.getClientSex());

		if (chargingData.getClientChargingAdress() != null) {
			transaction.getCharging().setClientChargingAdress(new AddressData());
			transaction.getCharging().getClientChargingAdress()
					.setCity(chargingData.getClientChargingAdress().getCity());
			transaction.getCharging().getClientChargingAdress()
					.setComplement(chargingData.getClientChargingAdress().getComplement());
			transaction.getCharging().getClientChargingAdress()
					.setCountry(chargingData.getClientChargingAdress().getCountry());
			transaction.getCharging().getClientChargingAdress()
					.setDistrict(chargingData.getClientChargingAdress().getDistrict());
			transaction.getCharging().getClientChargingAdress()
					.setNumber(chargingData.getClientChargingAdress().getNumber());
			transaction.getCharging().getClientChargingAdress()
					.setState(chargingData.getClientChargingAdress().getState());
			transaction.getCharging().getClientChargingAdress()
					.setStreet(chargingData.getClientChargingAdress().getStreet());
			transaction.getCharging().getClientChargingAdress()
					.setZipCode(chargingData.getClientChargingAdress().getZipCode());
		}

		if (chargingData.getClientChargingPhone() != null && !chargingData.getClientChargingPhone().isEmpty()) {
			List<PhoneData> myPhoneList = new ArrayList<>();

			for (PhoneData phone : chargingData.getClientChargingPhone()) {
				PhoneData myPhone = new PhoneData();
				myPhone.setDdd(phone.getDdd());
				myPhone.setDdi(phone.getDdi());
				myPhone.setPhone(phone.getPhone());
				myPhone.setPhoneType(phone.getPhoneType());
				myPhoneList.add(myPhone);
			}

			transaction.getCharging().setClientChargingPhone(myPhoneList);
		}
	}

}
