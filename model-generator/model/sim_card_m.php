<?php

if (!defined('BASEPATH'))
    exit('No direct script access allowed');

class Sim_card_m extends MY_Model {
	
	protected $table = 'sim_card';
	
	function __construct() {
		parent :: __construct();
	}
}
