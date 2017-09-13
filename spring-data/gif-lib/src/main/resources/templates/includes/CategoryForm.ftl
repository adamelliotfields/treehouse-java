[#ftl]
[#import "/spring.ftl" as spring]
<div class="container">
  <form action="${action}" method="post" th:object="${category}">
    <input type="hidden" th:field="*{id}"/>
    <div class="row">
      <div class="col s12">
        <h2>${heading}</h2>
      </div>
    </div>
    <div class="divider"></div>
    <div class="row">
      <div class="${fields.hasErrors('name')?then('col s12 l8 error', 'col s12 l8')}">
        <input type="text" th:field="*{name}" placeholder="Category Name"/>
        <div class="error-message" th:if="${#fields.hasErrors('name')}" th:errors="*{name}"></div>
      </div>
    </div>
    <div class="row">
      <div class="${fields.hasErrors('colorCode')?then('col s12 l8 error', 'col s12 l8')}">
        <select th:field="*{colorCode}" class="cs-select cs-skin-border">
          <option value="" disabled="disabled">Category Color</option>
          [#list colors as color]
          <option value="${color.hexCode}" style="color: ${color.hexCode}">${color.name}</option>
          [/#list]
        </select>
        <div class="error-message" th:if="${#fields.hasErrors('colorCode')}" th:errors="*{colorCode}"></div>
      </div>
    </div>
    <div class="row">
      <div class="col s12 l8">
        <button type="submit" class="button">${submit}</button>
        <a href="/categories" class="button">Cancel</a>
      </div>
    </div>
  </form>
  <div class="row delete" id="${category.id != null}">
    <div class="col s12 l8">
      <form action="/categories/${category.id}/delete" method="post">
        <button type="submit" class="button">Delete</button>
      </form>
    </div>
  </div>
</div>
