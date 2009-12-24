grammar JPath;

@header {
package com.gu.jpath;
}

query returns[List<String> result]
scope { List identifiers; }
@init {
	$query::identifiers = new ArrayList<String>();
}
	:(
		identifier (OPERATOR identifier)*
		EOF
	 )
	 { $result = $query::identifiers; }
	;

identifier
	:	(i=IDENTIFIER
	|
	)
	{ $query::identifiers.add($i.getText()); }
	;

IDENTIFIER : ('a'..'z'|'A'..'Z'|'-'|'_')+;
OPERATOR: '.';
EOF: '<EOF>';