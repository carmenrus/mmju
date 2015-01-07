<?php

if (!defined('BASEPATH'))
    exit('No direct script access allowed');

class Sim_stock_m extends MY_Model {
	
	protected $table = 'sim_stock';
	
	function __construct() {
		parent :: __construct();
	}
}
