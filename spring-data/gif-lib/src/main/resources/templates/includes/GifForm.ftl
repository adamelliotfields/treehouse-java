[#ftl]
[#import "/spring.ftl" as spring]
<div class="container upload">
  <div class="row">
    <div class="col s12">
      <h2>${heading}</h2>
      <div class="subtitle">Upload and share your GIFs with friends and family on Facebook, Twitter, and everywhere else.</div>
    </div>
  </div>
  <div class="divider"></div>
  <form action="${action}" method="post" enctype="multipart/form-data">
    <input type="hidden" th:field="*{id}"/>
    <div class="row">
      <div class="col s12 l8">
        <div class="file-wrapper">
          <input type="file" id="file" name="file"/>
          <span class="placeholder" data-placeholder="Choose an image...">Choose an image...</span>
          <label for="file" class="button">Browse</label>
        </div>
      </div>
    </div>
    <div class="row">
      <div class="col s12 l8">
        <input type="text" th:field="*{description}" placeholder="Description"/>
      </div>
    </div>
    <div class="row">
      <div class="col s12 l8">
        <select th:field="*{category.id}" class="cs-select cs-skin-border">
          <option value="" disabled="disabled">Select a category</option>
          <option th:each="cat : ${categories}" th:value="${cat.id}" th:text="${cat.name}" th:style="|color:${cat.colorCode}|">Technology</option>
        </select>
      </div>
    </div>
    <div class="row">
      <div class="col s12">
        <button th:text="${submit}" type="submit" class="button">Upload</button>
        <a href="javascript:window.location = document.referrer;" class="button">Cancel</a>
      </div>
    </div>
  </form>
</div>