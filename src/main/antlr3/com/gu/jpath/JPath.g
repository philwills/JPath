grammar JPath;

@header {
package com.gu.jpath;
}

@lexer::header{ package com.gu.jpath; } 

query returns[List<QueryToken> result]
scope { List identifiers; }
@init {
	$query::identifiers = new ArrayList<QueryToken>();
}
	:(
		queryToken (OPERATOR queryToken)*
		EOF
	 )
	 { $result = $query::identifiers; }
	;

queryToken
	:	(i=identifier ARRAY_ACCESS_START d=digit ARRAY_ACCESS_END)
	{ $query::identifiers.add(new ArrayAccessQueryToken(i, d)); }
	|
	(i=identifier)
	{ $query::identifiers.add(new DirectAccessQueryToken(i)); }
	;

identifier returns [String id]
	:	i=IDENTIFIER { $id = $i.getText(); }
	|
	;

digit returns [Integer digit]
	:	d=DIGIT { $digit = new Integer($d.getText()); }
	|
	;

IDENTIFIER : ('a'..'z'|'A'..'Z'|'-'|'_')+;
OPERATOR: '.';
ARRAY_ACCESS_START: '[';
DIGIT: '0'..'9'+;
ARRAY_ACCESS_END: ']';
EOF: '<EOF>';