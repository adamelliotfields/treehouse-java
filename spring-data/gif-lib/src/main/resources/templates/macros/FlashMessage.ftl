[#ftl]
[#macro getFlashMessage flash]
<div class="container">
  <i class="right material-icons" data-close="">close</i>
  <div class="flash ${flash.status?lower_case}">${flash.message}</div>
</div>
[/#macro]
