<?php

if (!defined('BASEPATH'))
    exit('No direct script access allowed');

class Member_m extends MY_Model {
	
	protected $table = 'member';
	
	function __construct() {
		parent :: __construct();
	}
}
