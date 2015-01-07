<?php

if (!defined('BASEPATH'))
    exit('No direct script access allowed');

class Role_m extends MY_Model {
	
	protected $table = 'role';
	
	function __construct() {
		parent :: __construct();
	}
}
