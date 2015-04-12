<head>
<script type="text/javascript" language="javascript" src="//code.jquery.com/jquery-1.11.1.min.js"></script>
<script type="text/javascript" language="javascript" src="//cdn.datatables.net/1.10.6/js/jquery.dataTables.min.js"></script>
<script type="text/javascript" class="display">
$(document).ready(function() {
	$('#example').dataTable();
} );


	</script>
</head>
<@extends src="base.ftl">
<@block name="header">You signed in as ${Context.principal}</@block>

<@block name="content">

<div style="margin: 10px 10px 10px 10px">
<B>
<center>This is the Gallary Apllication.</center>
</B>



	<#if gallaryDomains?size == 0>
		Empty message
	<#else>
				
		<table id="example" class="display" cellspacing="0" width="100%">
        <thead>
            <tr>
                <th>Title</th>
                <th>Id</th>
                <th>State</th>
                <th>Content</th>
                <th>download</th>
                
            </tr>
        </thead>
        <tbody>
		<#list gallaryDomains as doc>
            <tr>
                <td>${doc.title}</td>
                <td>${doc.id}</td>
                <td>${doc.state}</td>
                <td><img src=${doc.content} alt="Smiley face" height="77" width="77"></td>
                <td><a href=${doc.dowloadLink}>Download Image</a></td>
               
            </tr>
		</#list>
		</tbody>
</table>
		

	</#if>
</div>

</@block>
</@extends>
