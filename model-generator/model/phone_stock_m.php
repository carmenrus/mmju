<?php

if (!defined('BASEPATH'))
    exit('No direct script access allowed');

class Phone_stock_m extends MY_Model {
	
	protected $table = 'phone_stock';
	
	function __construct() {
		parent :: __construct();
	}
}
