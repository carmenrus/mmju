<?php

if (!defined('BASEPATH'))
    exit('No direct script access allowed');

class Phone_price_m extends MY_Model {
	
	protected $table = 'phone_price';
	
	function __construct() {
		parent :: __construct();
	}
}
