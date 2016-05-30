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
