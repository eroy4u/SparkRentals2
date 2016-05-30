<#include "base.ftl">


<#macro page_body>
  <h1>Spark Rentals</h1>
  <p><a href="/">Search for rentals</a></p>
  <#-- search form -->
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
        <label>Province</label>
        <select name="province" class="form-control">
          <option value="">- Select a province -</option>
          <#list provinceOptions as province>
            <option value="${province}" <#if data["province"]?? && data["province"] = province>selected</#if>>${province}</option>
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
        <label>Zip code</label>
        <input type="text" name="zipCode" value="<#if data["zipCode"]??>${data["zipCode"]?html}</#if>" class="form-control"/>
        <#--escape is needed to avoid xss -->
      </div>


      <div class="col-sm-4">
        <label>type</label>
        <select name="type" class="form-control">
          <option value="">- Select a type -</option>
          <#list typeOptions as type>
            <option value="${type}" <#if data["type"]?? && data["type"] = type>selected</#if>>${type}</option>
          </#list>
        </select>
      </div>
      
      <div class="col-sm-4">
        <label>Has Air condition</label>
        <select name="hasAirCondition" class="form-control">
          <option value="">- Select -</option>
          <#list yesNoOptions as option>
            <option value="${option}" <#if data["hasAirCondition"]?? && data["hasAirCondition"] = option>selected</#if>>${option}</option>
          </#list>
        </select>
      </div>
      
    </div>
    
    <div class="row">
      <div class="col-sm-4">
        <label>has Garden</label>
        <select name="hasGarden" class="form-control">
          <option value="">- Select -</option>
          <#list yesNoOptions as option>
            <option value="${option}" <#if data["hasGarden"]?? && data["hasGarden"] = option>selected</#if>>${option}</option>
          </#list>
        </select>
      </div>
      <div class="col-sm-4">
        <label>has Pool</label>
        <select name="hasPool" class="form-control">
          <option value="">- Select -</option>
          <#list yesNoOptions as option>
            <option value="${option}" <#if data["hasPool"]?? && data["hasPool"] = option>selected</#if>>${option}</option>
          </#list>
        </select>
      </div>
      <div class="col-sm-4">
        <label>is Close To Beach</label>
        <select name="isCloseToBeach" class="form-control">
          <option value="">- Select -</option>
          <#list yesNoOptions as option>
            <option value="${option}" <#if data["isCloseToBeach"]?? && data["isCloseToBeach"] = option>selected</#if>>${option}</option>
          </#list>
        </select>
      </div>
    </div>
    
    <div class="row">
      <div class="col-sm-4">
        <label>Number of rooms</label>
        <p>From
          <input name="roomsNumberFrom" type="number" value="<#if data["roomsNumberFrom"]??>${data["roomsNumberFrom"]?html}</#if>" class="form-control short-input"/>
          to
          <input name="roomsNumberTo" type="number" value="<#if data["roomsNumberTo"]??>${data["roomsNumberTo"]?html}</#if>" class="form-control short-input"/>
        </p>
      </div>
      <div class="col-sm-4">
        <label>Daily Price</label>
        <p>From
          <input name="dailyPriceFrom" type="number" step="0.01" value="<#if data["dailyPriceFrom"]??>${data["dailyPriceFrom"]?html}</#if>" class="form-control short-input"/>
          to
          <input name="dailyPriceTo" type="number" step="0.01" value="<#if data["dailyPriceTo"]??>${data["dailyPriceTo"]?html}</#if>" class="form-control short-input"/>
        </p>
      </div>
    </div>
    
    <div class="row">
      <div class="col-sm-4">
        <button type="submit" class="btn btn-primary form-control">Search</button>
      </div>
    </div>
  </form>
   
  <#-- search results table -->
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
    <#if errorMessages?size gt 0>
      <p>There are some errors in your input</p>
      <ul>
        <#list errorMessages as msg>
          ${msg}
        </#list>
      </ul>
      
    <#else>
      <p>There are no rentals matching your search criteria.</p>
    </#if>
  </#if>
  
</#macro>

<@display_page/>