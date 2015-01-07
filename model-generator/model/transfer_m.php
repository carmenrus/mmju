<?php

if (!defined('BASEPATH'))
    exit('No direct script access allowed');

class Transfer_m extends MY_Model {
	
	protected $table = 'transfer';
	
	function __construct() {
		parent :: __construct();
	}
}
