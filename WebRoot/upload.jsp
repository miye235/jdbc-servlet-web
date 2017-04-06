<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="US-ASCII"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=US-ASCII">
<title>Upload</title>
</head>
<body>
	<form name="UploadFprm" method="post" enctype="MULTIPART/FORM-DATA" action="upload">
		<table>
			<tr>
				<td><div align="right">User Name:</div></td>
				<td><input type="text" name="username" size="30"/></td>
			</tr>
			<tr>
				<td><div align="right">Upload File1</div></td>
				<td><input type="file" name="file1" size="30"/></td>
			</tr>
			<tr>
				<td><div align="right">Upload File2</div></td>
				<td><input type="file" name="file2" size="30"></td>
			</tr>
			<tr>
				<td><input type="submit" name="submit" value="upload"></td>
				<td><input type="reset" name="reset" value="reset"></td>
			</tr>
		</table>
	</form>
</body>
</html>