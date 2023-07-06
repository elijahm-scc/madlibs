<form action="/madlibs" method="post">
    <#list formFields?keys as fieldName> 
        ${formFields[fieldName]}
    </#list>
    <input type="submit" value="Submit">
</form>