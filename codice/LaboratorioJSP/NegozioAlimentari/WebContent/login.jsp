<%@ include file="header.jsp" %>
<form action="doLogin.jsp" method="post">
		<fieldset>
			<legend>Login</legend>
			<table align="center" width="75%">
				<tr>
					<th>Username</th>
					<td><input type="text" name="txtUsername"></td>
				</tr>
				<tr>
					<th>Password</th>
					<td><input type="password" name="txtPassword"></td>
				</tr>
								<tr>
					<td colspan="2" align="center"><input type="submit"
						name="invia" value="Invia"><input type="reset"
						name="cancella" value="Cancella"></td>
				</tr>
			</table>
		</fieldset>
	</form>