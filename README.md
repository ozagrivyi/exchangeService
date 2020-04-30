# exchangeService
# Currency exchange service

## 1. Prerequisities:
Make shure that docker is installed and add to path.<br/>
## 2. Authentication:
Spring basic auth with in-memory aurhentication has been used, so you can set it up via Postman or just pass 'Authorization' header in each request.<br/>
User: <br/>
	&nbsp;&nbsp;&nbsp;**Login**: user1<br/>
	&nbsp;&nbsp;&nbsp;**Password**: user1Secret<br/>
Header:<br/>
	&nbsp;&nbsp;&nbsp;**Authorization**: "Basic dXNlcjE6dXNlcjFTZWNyZXQ="<br/>
## 3. Launching:
To launch currency exchange service you should use run.bat (available in the root of the project).<br/>
You can use the following rest services when docker image is composed and deployed:<br/>
	a. GET: localhost:8080/api/exchange-rates - to get existing exchange pairs;<br/>
	b. POST: localhost:8080/api/exchange-rates - to set an exchage pair;<br/>
		To use this API you should pass the following data structure in request body:<br/>
		{<br/>
			&nbsp;&nbsp;&nbsp;**"from"**: "UAH",<br/>
			&nbsp;&nbsp;&nbsp;**"to"**: "USD",<br/>
			&nbsp;&nbsp;&nbsp;**"rate"**: 0.06<br/>
		}<br/>
		Where:<br/>
			&nbsp;&nbsp;&nbsp;**from** - currency from (three-letter currency code);<br/>
			&nbsp;&nbsp;&nbsp;**to** - currency to (three-letter currency code);<br/>
			&nbsp;&nbsp;&nbsp;**rate** - exchane rate;<br/>
		It will return http status code 200 in the case of success and the following data structure:<br/>
		{<br/>
			&nbsp;&nbsp;&nbsp;**"from"**: "UAH",<br/>
			&nbsp;&nbsp;&nbsp;**"to"**: "USD",<br/>
			&nbsp;&nbsp;&nbsp;**"rate"**: 0.06<br/>
		}<br/>
		It will return http status code 400 and an error message in the case of any error;<br/>
		It will return http status code 401 in the case of unauthorized access;<br/>
	c. GET: localhost:8080/api/commissions - to get available commissions;<br/>
	d. POST: localhost:8080/api/commissions - to set a commission;<br/>
		To use this API you should pass the following data structure in request body:<br/>
		{<br/>
			&nbsp;&nbsp;&nbsp;**"from"**: "UAH",<br/>
			&nbsp;&nbsp;&nbsp;**"to"**: "USD",<br/>
			&nbsp;&nbsp;&nbsp;**"commissionPt"**: 3<br/>
		}<br/>
		Where:<br/>
			&nbsp;&nbsp;&nbsp;**from** - currency from (three-letter currency code);<br/>
			&nbsp;&nbsp;&nbsp;**to** - currency to (three-letter currency code);<br/>
			&nbsp;&nbsp;&nbsp;**commissionPt** - exchane commission;<br/>
		It will return http status code 200 in the case of success and the following data structure:<br/>
		{<br/>
			&nbsp;&nbsp;&nbsp;**"from"**: "UAH",<br/>
			&nbsp;&nbsp;&nbsp;**"to"**: "USD",<br/>
			&nbsp;&nbsp;&nbsp;**"commissionPt"**: 3<br/>
		}<br/>
		It will return http status code 400 and an error message in the case of any error;<br/>
		It will return http status code 401 in the case of unauthorized access;<br/>
	e. POST: localhost:25100/api/exchange to exchage currencies;<br/>
		To use this API you should pass the following data structure in request body:<br/>
		{<br/>
			&nbsp;&nbsp;&nbsp;**"from"**: "UAH",<br/>
			&nbsp;&nbsp;&nbsp;**"to"**: "USD",<br/>
			&nbsp;&nbsp;&nbsp;**"amountFrom"**: "100",<br/>
			&nbsp;&nbsp;&nbsp;**"amountTo"**: "3.69",<br/>
			&nbsp;&nbsp;&nbsp;**"operationType"**: "GET"<br/>
		}<br/>
		Where:<br/>
			&nbsp;&nbsp;&nbsp;**from** - currency from (three-letter currency code);<br/>
			&nbsp;&nbsp;&nbsp;**to** - currency to (three-letter currency code);<br/>
			&nbsp;&nbsp;&nbsp;**amountFrom** - amount from;<br/>
			&nbsp;&nbsp;&nbsp;**amountTo** - amount to;<br/>
			&nbsp;&nbsp;&nbsp;**operationType** - two values are possible: GET and GIVE;<br/>
		It will return http status code 200 in the case of success and the following data structure:<br/>
		{<br/>
			&nbsp;&nbsp;&nbsp;**"from"**: "UAH",<br/>
			&nbsp;&nbsp;&nbsp;**"to"**: "USD",<br/>
			&nbsp;&nbsp;&nbsp;**"amountFrom"**: 100,<br/>
			&nbsp;&nbsp;&nbsp;**"amountTo"**: 1616.67,<br/>
			&nbsp;&nbsp;&nbsp;**"operationType"**: "GET"<br/>
		}<br/>
		Either amountFrom or amountTo will be changed depending on operationType;<br/>
		It will return http status code 400 and an error message in the case of any error;<br/>
		It will return http status code 401 in the case of unauthorized access;<br/>
 ## 4. Exceptions:
	The following message with http status code 400 will be return in the case when Commission with not existing exchange pair has been tried to add:<br/>
	"Exchange pair (FROM:TO) can not be found"<br/>
