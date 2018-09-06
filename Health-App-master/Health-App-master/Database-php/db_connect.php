<?php

	define("DB_HOST", "localhost");
	define("DB_USER", "root");
	define("DB_PASSWORD", "");
	define("DB_DATABASE", "Gynaecology");
	// connecting to mysql
	$con = mysqli_connect(DB_HOST, DB_USER, DB_PASSWORD, DB_DATABASE);
	if(!$con)//killing task if connection not established
	{
		die("Connection Could not be Established ".mysqli_error($conn));
	}
?>