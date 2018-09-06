<?php
//Database connections
require_once 'db_connect.php';
//Get JSON posted by Android Application
$start = $_POST["start"];
$stop = $_POST["stop"];
$p = 0;
if($start == 3 && $stop == 10)
{
	$p = $_POST["trimester"];
	$tri = $_POST["tablesJSON".$p.""];
if($p == 11)
{
	$table[11] = json_decode($tri);
	$t[11] = array();
}
else if($p == 12)
{
	$table[12] = json_decode($tri);
	$t[12] = array();
}
else if($p == 13)
{
	$table[13] = json_decode($tri);
	$t[13] = array();
}
}
$i=0;
for($s=1;$s<=2;$s++)
{
	$json[$s] = $_POST["tablesJSON".$s.""]; // stores the json response sent from the phone i.e stored data on phone
}
for($w=1;$w<=2;$w++)
{
	$table[$w] = json_decode($json[$w]); // decodes each received JSON into arrays
}
for($i=$start;$i<=$stop;$i++)
{
	$json[$i] = $_POST["tablesJSON".$i.""]; // stores the json response sent from the phone i.e stored data on phone
}
for($j=1;$j<=2;$j++)
{
	$table[$j] = json_decode($json[$j]); // decodes each received JSON into arrays
}
for($j=$start;$j<=$stop;$j++)
{
	$table[$j] = json_decode($json[$j]); // decodes each received JSON into arrays
}
$t[1] = array(); //gen info
$t[2] = array(); // doc table
for($z=$start;$z<=$stop;$z++)
{
	$t[$z] = array(); //arrays created so as to post back reponse from server to the phone
}

$TABLE = array(); // String array containing all names of tables
$TABLE[1] = "general_information";
$TABLE[2] = "doctor_table";
$TABLE[3] = "obstetric_information";
$TABLE[4] = "investigations";
$TABLE[5] = "obstetric_score";
$TABLE[6] = "past_obstetric_history";
$TABLE[7] = "history_table";
$TABLE[8] = "personal_history";
$TABLE[9] = "general_physical_examination";
$TABLE[10] = "systemic_examination";
$TABLE[11] = "first_trimester";
$TABLE[12] = "second_trimester";
$TABLE[13] = "third_trimester";
$TABLE[14] = "gynaec_complaints";
$TABLE[15] = "gynaec_obstetric_history";
$TABLE[16] = "gynaec_past_history";
$TABLE[17] = "gynaec_family_history";
$TABLE[18] = "gynaec_personal_history";
$TABLE[19] = "gynaec_physical_examination";
$TABLE[20] = "gynaec_systemic_examination";
$TABLE[21] = "gynaec_investigations";
$TABLE[22] = "gynaec_care";
/// deleteing for updating 
/*
for($v=1;$v=2;$v++)
{
	for($i = 0; $i < sizeof($table[$v]); $i++)
	{
		$patient_id2 = $table[$v][$i]->patient_id;
		//echo $patient_id;
		$delete2 = "DELETE FROM ".$TABLE[$v]." WHERE Patient_id='".$patient_id2."'";
		$del2 = mysqli_query($con,$delete2);
	}
}
for($f=$stop;$f>=$start;$f--)
{
	for($i = 0; $i < sizeof($table[$f]); $i++)
	{
		$patient_id = $table[$f][$i]->patient_id;
		//echo $patient_id;
		$delete = "DELETE FROM ".$TABLE[$f]." WHERE Patient_id='".$patient_id."'";
		$del = mysqli_query($con,$delete);
	}
}
for($i = 0; $i < sizeof($table[$p]); $i++)
{
	$patient_id1 = $table[$p][$i]->patient_id;
	$delete1 = "DELETE FROM ".$TABLE[$p]." WHERE Patient_id='".$patient_id1."'";
	$del1 = mysqli_query($con,$delete1);
}
*/
// delete completed

mysqli_autocommit($con,FALSE);
$cnt = false;
$a = array(); // universal array that posts response back containing all individual responses per table 

for($k=1;$k<=2;$k++) //looping through each table and filling in appropriate posted values from the phone
{
	for($i = 0; $i < sizeof($table[$k]); $i++)//looping through all column names and getting corresponding values
	{
		$z = (array)$table[$k][$i];
		foreach ($z as &$value) { // making coloumn names ready to put in as record similar to sql statement
			$value = "'".$value."'";
		}
		unset($value);
		$y = implode(",",$z);
		//echo $y."\n";
		$patient_id = $table[$k][$i]->patient_id;
		//echo $patient_id;
		$delete = "DELETE FROM ".$TABLE[$k]." WHERE Patient_id='".$patient_id."'";
		mysqli_query($con,$delete);
		$result = mysqli_query($con,"INSERT INTO ".$TABLE[$k]." VALUES(".$y.")"); //query to insert
		if($result)
		{
			$t[$k]['RegistrationNo'] = $table[$k][$i]->patient_id;
			$t[$k]['tableName'] = $TABLE[$k];
			$t[$k]['updateStatus'] = 'yes';
			array_push($a,$t[$k]);
			$cnt = true;
		}
		else{
			if( mysqli_errno($con) == 1062) {
				// Duplicate key - Primary Key Violation
				echo 'Duplicate Key';
			} else {
			$t[$k]['RegistrationNo'] = $table[$k][$i]->patient_id;
			$t[$k]['tableName'] = $TABLE[$k];
			$t[$k]['updateStatus'] = 'no';
				array_push($a,$t[$k]);
				$cnt = false;
				mysqli_rollback($con);// Undo changes which are not saved 
				break 2;//breaking out of all loops if the sync is unsuccessful 
			}            
		}
	}
}

for($k=$start;$k<=$stop;$k++) //looping through each table and filling in appropriate posted values from the phone
{
	for($i = 0; $i < sizeof($table[$k]); $i++)//looping through all column names and getting corresponding values
	{
		$z = (array)$table[$k][$i];
		foreach ($z as &$value) { // making coloumn names ready to put in as record similar to sql statement
			$value = "'".$value."'";
		}
		unset($value);
		$y = implode(",",$z);
		//echo $y."\n";
		$patient_id = $table[$k][$i]->patient_id;
		//echo $patient_id;
		$delete = "DELETE FROM ".$TABLE[$k]." WHERE Patient_id='".$patient_id."'";
		mysqli_query($con,$delete);
		$result = mysqli_query($con,"INSERT INTO ".$TABLE[$k]." VALUES(".$y.")"); //query to insert
		if($result)
		{
			$t[$k]['RegistrationNo'] = $table[$k][$i]->patient_id;
			$t[$k]['tableName'] = $TABLE[$k];
			$t[$k]['updateStatus'] = 'yes';
			array_push($a,$t[$k]);
			$cnt = true;
		}
		else{
			if( mysqli_errno($con) == 1062) {
				// Duplicate key - Primary Key Violation
				echo 'Duplicate Key';
			} else {
			$t[$k]['RegistrationNo'] = $table[$k][$i]->patient_id;
			$t[$k]['tableName'] = $TABLE[$k];
			$t[$k]['updateStatus'] = 'no';
				array_push($a,$t[$k]);
				$cnt = false;
				mysqli_rollback($con);// Undo changes which are not saved 
				break 2;//breaking out of all loops if the sync is unsuccessful 
			}            
		}
	}
}

if($p != 0)
{
for($i = 0; $i < sizeof($table[$p]); $i++)//looping through all column names and getting corresponding values
	{
		$z = (array)$table[$p][$i];
		foreach ($z as &$value) { // making coloumn names ready to put in as record similar to sql statement
			$value = "'".$value."'";
		}
		unset($value);
		$y = implode(",",$z);
		//echo $y."\n";
		$patient_id = $table[$p][$i]->patient_id;
		//echo $patient_id;
		$delete = "DELETE FROM ".$TABLE[$p]." WHERE Patient_id='".$patient_id."'";
		mysqli_query($con,$delete);
		$result = mysqli_query($con,"INSERT INTO ".$TABLE[$p]." VALUES(".$y.")"); //query to insert
		if($result)
		{
			$t[$k]['RegistrationNo'] = $table[$p][$i]->patient_id;
			$t[$k]['tableName'] = $TABLE[$p];
			$t[$k]['updateStatus'] = 'yes';
			array_push($a,$t[$p]);
			$cnt = true;
		}
		else{
			if( mysqli_errno($con) == 1062) {
				// Duplicate key - Primary Key Violation
				echo 'Duplicate Key';
			} else {
			$t[$p]['RegistrationNo'] = $table[$k][$i]->patient_id;
			$t[$p]['tableName'] = $TABLE[$k];
			$t[$p]['updateStatus'] = 'no';
				array_push($a,$t[$p]);
				$cnt = false;
				mysqli_rollback($con);// Undo changes which are not saved 
				break 1;//breaking out of all loops if the sync is unsuccessful 
			}            
		}
	}
}

if($cnt)
	{
		mysqli_commit($con);
	}
	echo json_encode($a); //giving response back to phone as acknowledment/ handshake information

?>