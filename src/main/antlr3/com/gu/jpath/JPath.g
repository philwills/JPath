grammar JPath;

@header {
package com.gu.jpath;
}

query returns[List<String> result]
@init {
	result = new ArrayList<String>();
}
	:(
		identifierResult=identifier {result.add(identifierResult);}
		( OPERATOR identifierResult=identifier {result.add(identifierResult);} )*
		EOF
	 )
	;

identifier returns[String parsedItem]
	:	i=IDENTIFIER { $parsedItem = i.getText(); }
	|
	;

IDENTIFIER : ('a'..'z'|'A'..'Z'|'-'|'_')+;
OPERATOR: '.';
EOF: '<EOF>';