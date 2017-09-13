[#ftl]
[#macro getNavbar springMacroRequestContext]
<div class="navbar-fixed">
  <nav>
    <div class="container">
      <a href="/" class="brand-logo">gif<span>.</span>lib</a>
      <a href="#" data-activates="mobile-nav" class="button-collapse right"><i class="material-icons">menu</i></a>
      [#local uri = springMacroRequestContext.requestUri]
      <ul class="right hide-on-med-and-down">
        <li [#if uri == "/upload"]class="active"[/#if]>
          <a href="/upload">Upload</a>
        </li>
        <li [#if uri == "/"]class="active"[/#if]>
          <a href="/">Explore</a>
        </li>
        <li [#if uri == "/categories"]class="active"[/#if]>
          <a href="/categories">Categories</a>
        </li>
        <li [#if uri == "/favorites"]class="active"[/#if]>
          <a href="/favorites">Favorites</a>
        </li>
      </ul>
      <ul id="mobile-nav" class="side-nav">
        <li [#if uri == "/upload"]class="active"[/#if]>
          <a href="/upload">Upload</a>
        </li>
        <li [#if uri == "/"]class="active"[/#if]>
          <a href="/">Explore</a>
        </li>
        <li [#if uri == "/categories"]class="active"[/#if]>
          <a href="/categories">Categories</a>
        </li>
        <li [#if uri == "/favorites"]class="active"[/#if]>
          <a href="/favorites">Favorites</a>
        </li>
      </ul>
    </div>
  </nav>
</div>
[/#macro]
