<?php

if (!defined('BASEPATH'))
    exit('No direct script access allowed');

class Branch_m extends MY_Model {
	
	protected $table = 'branch';
	
	function __construct() {
		parent :: __construct();
	}
}
