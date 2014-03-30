<!DOCTYPE html>
<html>
<head>
    <title>StaGen Test</title>
	<link rel="stylesheet" type="text/css" href="my.css"/>
</head>

<body>
    <section>
        <h2>Name: $name$</h2>
        <p>Spouse: <i>$spouse$</i></p>
        
        <ol>
        $interests: {interest|
        	<li>$interest$</li>
        }$
        </ol>
    </section>
    
    <section>
    	$_content$
    </section>
</body>
</html>
