[#ftl]
[#macro getSearchbar]
<div class="search-bar container">
  <div class="row">
    <div class="col s12">
      <form action="/search" method="get">
        <div class="input-field">
          <input name="q" type="search" placeholder="Search all gifs..." required="required" autocomplete="off"/>
          <i class="material-icons">search</i>
        </div>
      </form>
    </div>
  </div>
</div>
[/#macro]
