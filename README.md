# exchangeService
Currency exchange service

1. Prerequisities:
Make shure that docker is installed and add to path.
2. Launching:
To launch currency exchange service you should use run.bat (available in the root of the project).
You can use the following rest services when docker image is composed and deployed:
	a. GET: localhost:8080/api/exchange-rates - to get existing exchange pairs;
	b. POST: localhost:8080/api/exchange-rates - to set an exchage pair;
		To use this API you should pass the following data structure in request body:
		{
			"from": "UAH",
			"to": "USD",
			"rate": 0.06
		}
		Where:
			from - currency from (three-letter currency code);
			to - currency to (three-letter currency code);
			rate - exchane rate;
		It will return http status code 200 in the case of success and the following data structure:
		{
			"from": "UAH",
			"to": "USD",
			"rate": 0.06
		}
		This means that data regarding exchange pair is saved successfully;
		It will return http status code 400 and an error message in the case of any error;
		It will return http status code 401 in the case of unauthorized access;
	c. GET: localhost:8080/api/commissions - to get available commissions;
	d. POST: localhost:8080/api/commissions - to set a commission;
		To use this API you should pass the following data structure in request body:
		{
			"from": "UAH",
			"to": "USD",
			"commissionPt": 3
		}
		Where:
			from - currency from (three-letter currency code);
			to - currency to (three-letter currency code);
			commissionPt - exchane commission;
		It will return http status code 200 in the case of success and the following data structure:
		{
			"from": "UAH",
			"to": "USD",
			"commissionPt": 3
		}
		This means that data regarding the commission is saved successfully
		It will return http status code 400 and an error message in the case of any error;
		It will return http status code 401 in the case of unauthorized access;
	e. POST: localhost:25100/api/exchange to exchage currencies;
		To use this API you should pass the following data structure in request body:
		{
			"from": "UAH",
			"to": "USD",
			"amountFrom": "100",
			"amountTo": "3.69",
			"operationType": "GET"
		}
		Where:
			from - currency from (three-letter currency code);
			to - currency to (three-letter currency code);
			amountFrom - amount from;
			amountTo - amount to;
			operationType - two values are possible: GET and GIVE;
		It will return http status code 200 in the case of success and the following data structure:
		{
			"from": "UAH",
			"to": "USD",
			"amountFrom": 100,
			"amountTo": 1616.67,
			"operationType": "GET"
		}
		Either amountFrom or amountTo will be changed depending on operationType;
		This means that data regarding the commission is saved successfully
		It will return http status code 400 and an error message in the case of any error;
		It will return http status code 401 in the case of unauthorized access;