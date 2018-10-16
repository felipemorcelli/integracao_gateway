<?php 

	require_once "json_representation/transaction_json.php";
	require_once "json_representation/transaction_data_json.php";
	require_once "json_representation/transaction_card_data_json.php";

	class TransactionJsonBuilder{
		

		public function newTransaction($transaction){
			$jsonTransaction = new TransactionJson();
			$jsonTransaction->transacao = $this->newTransactionData($transaction->transactionData);
			$jsonTransaction->dadosCartao = $this->newTransactionCardData($transaction->transactionCardData);

			return $jsonTransaction;
		}

		public function newTransactionData($transactionData){
			$jsonTransactionData = new TransactionDataJson();

			return $jsonTransactionData;
		}

		public function newTransactionCardData($transactionCardData){
			$jsonTransactionCardData = new TransactionCardData();

			return $jsonTransactionCardData;
		}
	}

 ?>