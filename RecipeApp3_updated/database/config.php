<?php
    define("DB_HOST", "localhost");
    define("DB_USER", "root");
    //define("DB_PASSWORD", "root");// mayank's password
    define("DB_PASSWORD", "");
    define("DB_NAME", "recipe");
    $con = mysqli_connect(DB_HOST, DB_USER, DB_PASSWORD, DB_NAME);

    ?>