[#ftl]
<div class="categories container">
  <div class="row">
    <div class="col s9">
      <h2>Categories</h2>
    </div>
    <div class="col s3">
      <a class="right add" href="/categories/add">+</a>
    </div>
  </div>
  <div class="divider"></div>
  <div class="row">
    [#list categories as cat]
    <div class="col s12 l4">
      <div class="card" style="background-color: ${cat.colorCode}">
        <div class="card-content">
          <div class="card-title">
            <a class="name" href="/categories/${cat.id}">${cat.name}</a>
            <a class="edit" href="/categories/${cat.id}/edit">
              <i class="hide-on-med-and-down material-icons">more_horiz</i>
              <i class="hide-on-large-only medium material-icons">more_horiz</i>
            </a>
          </div>
        </div>
      </div>
    </div>
    [/#list]
  </div>
</div>
