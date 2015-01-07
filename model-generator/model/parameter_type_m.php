<?php

if (!defined('BASEPATH'))
    exit('No direct script access allowed');

class Parameter_type_m extends MY_Model {
	
	protected $table = 'parameter_type';
	
	function __construct() {
		parent :: __construct();
	}
}
