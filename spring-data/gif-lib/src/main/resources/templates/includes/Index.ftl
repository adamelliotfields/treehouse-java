[#ftl]
<div class="gifs container">
  <div class="row">
    [#list gifs as gif]
    <div class="col s12 m6 l4">
      <a href="/gifs/${gif.id}" class="actions">
        <img src="https://media1.giphy.com/media/${gif.id}/giphy.gif" />
        [#--<img src="/gifs/${gif.id}.gif" alt="gif" />--]
        <form action="/gifs/${gif.id}/favorite" method="post">
          <button type="submit" class="${favorite?then('unmark favorite', 'mark favorite')}"></button>
        </form>
      </a>
    </div>
    [/#list]
  </div>
</div>
