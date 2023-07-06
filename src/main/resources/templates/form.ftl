<form action="/madlibs?cmd=view&storyid=${storyid}" method="post">
    <#list formFields?keys as fieldName> 
        ${formFields[fieldName]}
    </#list>
    <input type="submit" value="Submit">
</form>