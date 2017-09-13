[#ftl]
<div class="row user-meta">
  <div class="col s3 l12">
    <img class="responsive-img circle" src="/images/me.jpg" alt="avatar" />
  </div>
  <div class="col s9= l12">
    <h2>${username}</h2>
    <div class="meta">${gifs?size} Favorites></div>
  </div>
</div>
<div class="gifs container">
  <div class="divider"></div>
  <div class="row">
    [#list gifs as gif]
    <div class="col s12 l4">
      <a href="/gifs/${gif.id}">
        <img src="/gifs/${gif.id}.gif" />
        [#assign favorite = gif.favorite]
        <a href="#" class="${favorite?then('unmark favorite', 'mark favorite')}"></a>
      </a>
    </div>
    [/#list]
  </div>
</div>
