<?php
//Database connections
require_once 'db_connect.php';
$patientId = $_POST["ID"];
//$patientId = '050417045339'; 
$final = array();
$TABLE = array();
//$TABLE[1] = "gynaec_care";
//$TABLE[2] = "gynaec_complaints";
/* */
$TABLE[1] = "general_information";
$TABLE[2] = "doctor_table";
$TABLE[3] = "obstetric_information";
$TABLE[4] = "first_trimester";
$TABLE[5] = "second_trimester";
$TABLE[6] = "third_trimester";
$TABLE[7] = "investigations";
$TABLE[8] = "obstetric_score";
$TABLE[9] = "past_obstetric_history";
$TABLE[10] = "history_table";
$TABLE[11] = "personal_history";
$TABLE[12] = "general_physical_examination";
$TABLE[13] = "systemic_examination";
$TABLE[14] = "gynaec_complaints";
$TABLE[15] = "gynaec_obstetric_history";
$TABLE[16] = "gynaec_past_history";
$TABLE[17] = "gynaec_family_history";
$TABLE[18] = "gynaec_personal_history";
$TABLE[19] = "gynaec_physical_examination";
$TABLE[20] = "gynaec_systemic_examination";
$TABLE[21] = "gynaec_investigations";
$TABLE[22] = "gynaec_care";
/* */
//$result = mysqli_query($con,"SELECT* FROM ".$TABLE[1]." WHERE Patient_id=".$patientId);
$result = "SELECT* FROM ".$TABLE[1]." WHERE Patient_id='".$patientId."'";
$query = mysqli_query($con,$result);
//$length = mysqli_num_fields($query);
/*
while($row = mysqli_fetch_array($query)) {
	for($j=0;$j<$length;$j++)
		echo $row[$j]."\n";
}*/
//echo $query;
if(mysqli_num_rows($query)>0)
{
	//echo "Hello";
	for($i = 1;$i<=22;$i++)
	{
			$result1 = "SELECT* FROM ".$TABLE[$i]." WHERE Patient_id='".$patientId."'"; //query to select
			$query1 = mysqli_query($con,$result1);
			if(mysqli_num_rows($query1) > 0)
			{				
				$length = mysqli_num_fields($query1);
				if(mysqli_num_rows($query1) > 1)
				{
					$num = mysqli_num_rows($query1);
					//echo $num;
					$cont = array();
					$pers = array();
					$k =0;
					while($row = mysqli_fetch_array($query1))
					{
						$k++;
						$pers[$k] = (string)$k;
						$g[$k] = array();
						for($j=0;$j<$length;$j++)
						{
							$g[$k]["C".(string)$j] = $row[$j];
						}
						$cont[$pers[$k]] = $g[$k];
					}
					$final[$TABLE[$i]] = $cont;
				}
				else
				{
					$t[$i] = array();
					while($row = mysqli_fetch_array($query1))
					{
						for($j=0;$j<$length;$j++)
						{
							$t[$i]["C".(string)$j] = $row[$j];
						}
						//array_push($final,$t[$i]);
					}
					$final[$TABLE[$i]] = $t[$i];
				}
				//$x = array($TABLE[$i]=>$t[$i]);
			}
	}
	//print_r($final[$TABLE[1]]);
	echo json_encode($final);
}
?>