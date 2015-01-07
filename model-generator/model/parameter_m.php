<?php

if (!defined('BASEPATH'))
    exit('No direct script access allowed');

class Parameter_m extends MY_Model {
	
	protected $table = 'parameter';
	
	function __construct() {
		parent :: __construct();
	}
}
