<#include "base.ftl">

<#macro page_body>
  <h1>Spark Rentals</h1>
  <p>Search for rentals</p>
  <form method="GET" action="/">
    
    <div class="row">
      <div class="col-sm-4">
        <label>City</label>
        <select name="city" class="form-control">
          <option value="">- Select a city -</option>
          <#list cityOptions as city>
            <option value="${city}" <#if data["city"]?? && data["city"] = city>selected</#if>>${city}</option>
          </#list>
        </select>
      </div>
      <div class="col-sm-4">

        <label>Country</label>
        <select name="country" class="form-control">
          <option value="">- Select a country -</option>
          <#list countryOptions as country>
            <option value="${country}" <#if data["country"]?? && data["country"] = country>selected</#if>>${country}</option>
          </#list>
        </select>
      </div>
    </div>
    <div class="row">
      <div class="col-sm-4">
        <button type="submit" class="btn btn-primary form-control">Search</button>
      </div>
    </div>
  </form>
  
  <#if rentalList?size gt 0>
    <table class="table">
      <tr>
        <th>ID</th>
        <th>city</th>
        <th>province</th>
        <th>country</th>
        <th>zip code</th>
        <th>type</th>
        <th>has air condition</th>
        <th>has garden</th>
        <th>has pool</th>
        <th>is close to beach</th>
        <th>daily price</th>
        <th>currency</th>
        <th>number of rooms</th>
      </tr>
      <#list rentalList as rental>
        <tr>
          <td>${rental.id}</td>
          <td>${rental.city}</td>
          <td>${rental.province}</td>
          <td>${rental.country}</td>
          <td>${rental.zipCode}</td>
          <td>${rental.type}</td>
          <td>${rental.hasAirCondition?string('Yes', 'No')}</td>
          <td>${rental.hasGarden?string('Yes', 'No')}</td>
          <td>${rental.hasPool?string('Yes', 'No')}</td>
          <td>${rental.isCloseToBeach?string('Yes', 'No')}</td>
          <td>${rental.dailyPrice}</td>
          <td>${rental.currency}</td>
          <td>${rental.roomsNumber}</td>
        </tr>
      </#list>
    </table>
  <#else>
    <p>There are no rentals matching your search criteria.</p>
  </#if>
  
</#macro>

<@display_page/>