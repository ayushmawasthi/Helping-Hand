<?php
$mess= $_POST["data"];
$spe="split123split";
try
{
$mysqli=new mysqli("fdb33.awardspace.net","3880528_data","final@2022","3880528_data");
if($mysqli)
{
        $result=mysqli_query($mysqli,"SELECT * FROM message");
        while($row = mysqli_fetch_array($result))
        {
                echo ($row['f1'].$spe.$row['f2']);
                break;
        }
}
else
{
        throw new Exception('Unable to connect');
}
}
      catch(Exception $e)
{
    echo $e;
}
  



?>