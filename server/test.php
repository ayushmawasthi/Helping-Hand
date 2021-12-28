<?php
//$phone=filter_input(INPUT_POST,"data");
$phone = file_get_contents("php://input");
$arr=explode("hxhx",$phone);
 $flex1=$arr[0];
 $flex2=$arr[1];
 echo $phone;

$mysqli=new mysqli("fdb33.awardspace.net","3880528_data","final@2022","3880528_data");
$sql="UPDATE message SET f1='".$flex1."'";
$result=mysqli_query($mysqli,$sql);
$sql="UPDATE message SET f2='".$flex2."'";
$result=mysqli_query($mysqli,$sql);
?>