<h4>Footer</h4>

<@s.url var="url" action="HelloWorld">
  <@s.param name="request_locale">en</@s.param>
</@s.url>

<@s.a href="%{url}">English</@s.a>

EL: ${url}

<@tiles.insertAttribute name="title"/>
