<?php

if (!defined('BASEPATH'))
    exit('No direct script access allowed');

class Township_m extends MY_Model {
	
	protected $table = 'township';
	
	function __construct() {
		parent :: __construct();
	}
}
