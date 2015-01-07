<?php

if (!defined('BASEPATH'))
    exit('No direct script access allowed');

class Transection_m extends MY_Model {
	
	protected $table = 'transection';
	
	function __construct() {
		parent :: __construct();
	}
}
