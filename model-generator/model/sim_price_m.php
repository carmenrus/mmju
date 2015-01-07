<?php

if (!defined('BASEPATH'))
    exit('No direct script access allowed');

class Sim_price_m extends MY_Model {
	
	protected $table = 'sim_price';
	
	function __construct() {
		parent :: __construct();
	}
}
