[#ftl]
<div class="gif-detail container">
  <div class="frame row">
    <div class="col s12 actions">
      <img src="https://media1.giphy.com/media/${gif.id}/giphy.gif" />
      [#--<img src="/gifs/${gif.id}.gif}" alt="${gif.description}" />--]
      <form action="/gifs/${gif.id}/favorite" method="post">
        [#assign favorite = gif.favorite]
        <button type="submit" class="${favorite?then('unmark favorite', 'mark favorite')}"></button>
      </form>
    </div>
  </div>
  <div class="meta row">
    <div class="col s10 l11 push-l1">
      <h4>${gif.description}</h4>
      <div>
        <span class="user">${gif.timeSinceUploaded} ago by ${gif.username}</span>
        <span class="category">
          <a href="/categories/${gif.category.id}" style="color: ${gif.category.colorCode}">${gif.category.name}</a>
        </span>
        <div class="actions">
          <a href="/gifs/${gif.id}/edit">Edit</a>
          <form action="/gifs/${gif.id}/delete" method="post">
            <button type="submit">Delete</button>
          </form>
        </div>
      </div>
    </div>
    <div class="col s2 l1 pull-l11">
      <img class="responsive-img circle" src="/images/me.jpg" alt="avatar" />
    </div>
  </div>
  <div class="row">
    <div class="col s12 l11 offset-l1">
      <div class="divider"></div>
    </div>
    <div class="col s12 l8 offset-l1">
      <div class="share">
        <a class="btn right" href="#">Copy</a>
        <span>http://gif.fy/${gif.id}</span>
      </div>
    </div>
  </div>
</div>
