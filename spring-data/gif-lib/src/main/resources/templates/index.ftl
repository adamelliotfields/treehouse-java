[#ftl]
[#import "macros/Meta.ftl" as Meta]
[#import "macros/Link.ftl" as Link]
[#import "macros/Navbar.ftl" as Navbar]
[#import "macros/Searchbar.ftl" as Searchbar]
[#import "macros/FlashMessage.ftl" as FlashMessage]
[#import "macros/Script.ftl" as Script]
[#import "functions/Template.ftl" as Template]
<!DOCTYPE html>
<html lang="en">
<head>
  [@Meta.getMetaTags /]
  [@Link.getLinkTags /]
  <title>giflib | ${title}</title>
</head>
<body>
  [@Navbar.getNavbar springMacroRequestContext /]

  [@Searchbar.getSearchbar /]

  [#if flash??]
  [@FlashMessage.getFlashMessage flash /]
  [/#if]

  [#include "includes/${Template.getTemplate(springMacroRequestContext)}.ftl"]

  [@Script.getScriptTags /]
</body>
</html>
