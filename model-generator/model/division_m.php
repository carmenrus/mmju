<?php

if (!defined('BASEPATH'))
    exit('No direct script access allowed');

class Division_m extends MY_Model {
	
	protected $table = 'division';
	
	function __construct() {
		parent :: __construct();
	}
}
