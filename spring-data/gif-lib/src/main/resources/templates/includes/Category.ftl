[#ftl]
<div class="gifs container">
  <h2 style="color: ${category.colorCode}">${category.name}</h2>
  <div class="row">
    [#list category.gifs as gif]
    <div class="col s12 l4">
      <a href="/gifs/${gif.id}">
        <img src="/gifs/${gif.id}.gif" />
        <a href="#" class="${favorite?then('unmark favorite', 'mark favorite')}"></a>
      </a>
    </div>
    [/#list]
  </div>
</div>
