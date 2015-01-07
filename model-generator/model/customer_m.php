<?php

if (!defined('BASEPATH'))
    exit('No direct script access allowed');

class Customer_m extends MY_Model {
	
	protected $table = 'customer';
	
	function __construct() {
		parent :: __construct();
	}
}
