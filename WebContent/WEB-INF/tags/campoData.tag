<%@ attribute name="id" required="true" %>

<!-- <input id="${id}" name="${id}"/> -->
		
<input id="${id}" name="${id}" value=""/>
<script>
$("#${id}").datepicker({dateFormat: 'dd/mm/yy'});
</script>