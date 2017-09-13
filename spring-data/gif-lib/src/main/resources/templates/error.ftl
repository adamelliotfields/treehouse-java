[#ftl]
<div class="container">
  <h2>${springMacroRequestContext.getErrors('javax.servlet.error.status_code')} Error</h2>
  <div class="app-error">
    <code>${springMacroRequestContext.getErrors('javax.servlet.error.exception')}</code>
  </div>
</div>
